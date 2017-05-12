/**
 * Created by Utilisateur on 09/05/2017.
 */
function GetCommande()
{
  var Json;
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/restaurant-ws/rest/commande/recup',
      // data: data,
      async: false,
      beforeSend: function (xhr) {
        if (xhr && xhr.overrideMimeType) {
          xhr.overrideMimeType('application/json;charset=utf-8');
        }
      },
      dataType: 'json',
      success: function (data) {
      Json = data;
      }
    });
    return Json;
}// getJson