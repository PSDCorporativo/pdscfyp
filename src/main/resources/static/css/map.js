// *
// * Adicionar um marcador simples
// * 2013 - www.marnoto.com
// *

 // variável que indica as coordenadas do centro do mapa
 var praiaBarra = new google.maps.LatLng(40.640416,-8.749541);

 // variável que indica as coordenadas do marcador
 var farolAveiro = new google.maps.LatLng(40.642851,-8.747596);

 function initialize() {
   var mapOptions = {
      center: praiaBarra, // variável com as coordenadas Lat e Lng
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.SATELLITE
   };
   var map = new google.maps.Map(document.getElementById("map-canvas"),
 mapOptions);
   
    // variável que define o URL para a nova imagem do marcador
   var minhaImagem = 'images/farol.png';

   // variável que define as opções do marcador
   var marker = new google.maps.Marker({
      position: farolAveiro, // variável com as coordenadas Lat e Lng
      map: map,
      title:"Farol de Aveiro",
      icon: minhaImagem // define a nova imagem do marcador
  });
}
google.maps.event.addDomListener(window, 'load', initialize);