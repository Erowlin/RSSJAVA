YUI().use('node', function(Y) {
    Y.delegate('click', function(e) {
        var idFeedItem = e.currentTarget.getAttribute('feed-item-id');

        console.log(idFeedItem);

        nodes = Y.all("[feed-item-id-hide="+ idFeedItem+"]");
        console.log(nodes);
        nodes.each(function (tata){
        	console.log(tata);
        	tata.toggleView();
        });
    }, document, '.feed-content-title');
});