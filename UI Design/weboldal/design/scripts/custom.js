/*custom.js*/

$(document).ready(function() {

  // Bal menü megjelenítése...
  $('.btn_sidebar').on('click', function(){
    event.preventDefault();
    $('.ui.sidebar').sidebar('setting', 'transition', 'overlay');
    $('.ui.sidebar').sidebar('toggle');
  });

  $('.mdl_foglalas').modal('attach events', '.btn_ujFoglalas', 'show');

}); //document.ready
