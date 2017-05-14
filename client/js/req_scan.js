function Scan(table,guid) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/restaurant-ws/rest/inscription/tag',
        data: 'table='+table+'&guid='+guid,
        dataType: 'x-www-form-urlencoded',
        success: function(retour) {
            console.log("Données:"+retour);
        },
        error: function(resultat, statut, erreur) {
            if(resultat.status==201) {
                alert("Ajout confirmer");
                document.location.href="scan.html";
            } else {
                console.log(resultat)
                console.log(statut);
                console.log(erreur);
                console.log("Echec:"+resultat.status);
            }
        }
    });
}