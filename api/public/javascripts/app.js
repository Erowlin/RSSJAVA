angular.module('rssjava', []).factory('Channels', ['$http', function($http) {
	return ({
		all: function () {
			return ($http.get('/channels'));
		},
		one: function (id) {
			return ($http.get('/channels/' + id));
		},
		create: function(channel) {
			return ($http.post('/channels/new', channel));
		},
		items: function(channel_id) {
			return ($http.get('/channels/' + channel_id + '/items'));
		},
		delete: function (channel_id) {
			return ($http.get('/channels/' + channel_id + '/delete'));
		}
		//...
	});
}]).controller('MainController', ['$scope', '$sce', 'Channels', function ($scope, $sce, Channels) {
	$scope.channels = [];
	$scope.items = [];

	Channels.all()
		.success(function (new_channels) {
			$scope.channels = new_channels;
		})
		.error(function (error) {
			console.log(error);
		})
	$scope.showItem = function (item) {
		if (item.show === undefined) {
			item.show = true;
		} else {
			item.show = !item.show;
		}
	};
	$scope.getItems = function (channel_id) {
		var channel = _.find($scope.channels, {id: channel_id});
		if (channel.items !== undefined) {
			$scope.items = channel.items;
		} else {
			Channels.items(channel_id)
				.success(function (items) {
					$scope.items = items;
					channel.items = items;
				})
				.error(function (error) {
					console.log(error);
				});
		}
	};
	$scope.createChannel = function () {
		Channels.create($scope.channel)
			.success(function (new_channel) {
				$scope.channels.push(new_channel);
			})
			.error(function (error) {
				console.log(error);
			});
	};
	$scope.deleteChannel = function (channel) {
		Channels.delete(channel.id)
			.success(function (res) {
				$scope.items = [];
				$scope.channels = _.reject($scope.channels, {id: channel.id});
			})
			.error(function (error) {
				console.log(error);
			})
	};
	$scope.loadHtml = function(html) {
		return $sce.trustAsHtml(html);
	}
}]);