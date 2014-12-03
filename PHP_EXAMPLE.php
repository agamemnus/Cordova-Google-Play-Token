// Validate a token and return an object containing its data.
function get_userinfo_from_external_server ($access_token, $service_name, $target_client_id) {
 switch ($service_name) {
  case "google_play" :
   // Validate that the token is for the appropriate client ID.
   $url = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=" . $access_token;
   $data = array();
   $options = array('http' => array(
    'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
    'method'  => 'GET',
    'content' => http_build_query($data)
    )
   );
   $context = stream_context_create($options);
   $service_server_result = @file_get_contents($url, false, $context);
   $tokeninfo = json_decode ($service_server_result);
   $received_client_id = $tokeninfo ->{"audience"};
   $received_client_id = substr ($received_client_id, 0, strpos($received_client_id, "-"));
   if ($received_client_id != $target_client_id) return FALSE;
   
   // Get the user info.
   $url = "https://www.googleapis.com/userinfo/v2/me?access_token=" . $access_token;
   $data = array();
   $options = array('http' => array(
    'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
    'method'  => 'GET',
    'content' => http_build_query($data)
    )
   );
  break;
 }
 $context = stream_context_create($options);
 $service_server_result = @file_get_contents($url, false, $context);
 
 if ($service_server_result === FALSE) return FALSE;
 return json_decode ($service_server_result);
}
