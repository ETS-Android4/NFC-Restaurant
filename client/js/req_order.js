function GetCommande() {
  var Json;
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/restaurant-ws/rest/commande/recup',
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
}
	
function Suppresion(idCommande) {
  console.log("lancement");
  $(document).ready(function(){
    var res = confirm("Retirer cette commande ?!");
    if (res == true) {
      $.ajax({
        url: 'http://localhost:8080/restaurant-ws/rest/commande/'+idCommande,
        type: 'DELETE',
        success: function(result) {
          window.location.reload();
        }
      });
    } else {
      console.log("Erreur groupe non Supprimer");
    }
  });
}