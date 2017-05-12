/**
 * Created by Utilisateur on 09/05/2017.
 */
function Connexion(username,password){
    var phone = username;
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/restaurant-ws/rest/connexion',
        data: 'phone='+phone+'&password='+password,
        dataType: 'x-www-form-urlencoded',
        success: function(retour){
            alert("Connexion succes ! ");
            console.log("Donnees retournees : " + retour );
        },
        error : function(resultat, statut, erreur){
            if(resultat.status==200){
                alert("Connexion effectuée!");
                // document.location.href="nouvellepage.html";
            }else{
                console.log(resultat)
                console.log(statut);
                console.log(erreur);
                console.log("Echec : "+resultat.status);
            }

        }
    });

}