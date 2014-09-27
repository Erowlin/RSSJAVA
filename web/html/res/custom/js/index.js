YUI().use('node', function(Y) {
    Y.delegate('click', function(e) {
        var idFeedItem = e.currentTarget.getAttribute('feed-item-id');


        node = Y.one("[feed-item-id-hide="+ idFeedItem+"]");
        node.toggleView();
    }, document, '.feed-content-title');
});