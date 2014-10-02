/*YUI().use('node', function(Y) {
    Y.delegate('click', function(e) {
        var idFeedItem = e.currentTarget.getAttribute('feed-item-id');


        node = Y.one("[feed-item-id-hide="+ idFeedItem+"]");
        node.toggleView();
    }, document, '.feed-content-title');
});*/

$('.nav-menu-button').click(function(event) {
    if ($('#nav').hasClass('active')) {
        $('.nav-inner').slideToggle(function() {
            $('#nav').toggleClass('active');
        });
    } else {
        $('.nav-inner').slideToggle();
        $('#nav').toggleClass('active');
    }

});

$('#toronto').click(function() {
    $('#content_toronto').slideToggle();
});