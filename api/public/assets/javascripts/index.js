angular.module('rssjava', [])
	.directive('slideable', function() {
		return {
			restrict: 'AC',
			compile: function(element, attr) {
				// wrap tag
				var contents = element.html();
				element.html('<div class="slideable_content" style="margin:0 !important; padding:0; height:auto !important" >' + contents + '</div>');
				return function postLink(scope, element, attrs) {
					// default properties
					attrs.duration = (!attrs.duration) ? '1s' : attrs.duration;
					attrs.easing = (!attrs.easing) ? 'ease-in-out' : attrs.easing;
					element.css({
						'overflow': 'hidden',
						'height': '0px',
						'transitionProperty': 'height',
						'transitionDuration': attrs.duration,
						'transitionTimingFunction': attrs.easing
					});
				};
			}
		};
	})
	.directive('slideToggle', function() {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				var target, content;
				attrs.expanded = false;
				element.bind('click', function() {
					if (!target) target = document.querySelector(attrs.slideToggle);
					if (!content) content = target.querySelector('.slideable_content');
					if (!attrs.expanded) {
						content.style.border = '1px solid rgba(0,0,0,0)';
						var y = content.clientHeight;
						content.style.border = 0;
						target.style.height = y + 'px';
					} else {
						target.style.height = '0px';
					}
					attrs.expanded = !attrs.expanded;
				});
			}
		}
	})
	.directive('selectableChannel', function() {
		return {
			restrict: 'AC',
			link: function(scope, element, attrs) {
				element.bind('click', function() {
					$('#list .feed-item-selected').removeClass('feed-item-selected');
					$(this).addClass('feed-item-selected');
				});
			}
		}
	})
	.factory('Channels', ['$http', function($http) {
		return ({
			all: function() {
				return ($http.get('/channels'));
			},
			one: function(id) {
				return ($http.get('/channels/' + id));
			},
			create: function(channel) {
				return ($http.post('/channels/new', channel));
			},
			items: function(channel_id) {
				return ($http.get('/channels/' + channel_id + '/items'));
			},
			delete: function(channel_id) {
				return ($http.get('/channels/' + channel_id + '/delete'));
			},
			read: function(channel_id, item_id, read) {
				return ($http.post('/channels/' + channel_id + '/items/' + item_id + '?read=' + read));
			}
		});
	}]).controller('MainController', ['$scope', '$sce', 'Channels', function($scope, $sce, Channels) {
		$scope.channels = [];
		$scope.items = [];

		Channels.all()
			.success(function(new_channels) {
				$scope.channels = new_channels;
			})
			.error(function(error) {
				console.log(error);
			})

		$scope.getItems = function(channel_id) {
			var channel = _.find($scope.channels, {
				id: channel_id
			});
			if (channel.items !== undefined) {
				$scope.items = channel.items;
			} else {
				Channels.items(channel_id)
					.success(function(items) {
						$scope.items = items;
						channel.items = items;
					})
					.error(function(error) {
						console.log(error);
					});
			}
		};
		$scope.setItemRead = function(item, read) {
			var p_id = $('#list .feed-item-selected').attr('id').substring(2);
			var channel = _.find($scope.channels, {
				id: parseInt(p_id)
			});
			Channels.read(p_id, item.id, read)
				.success(function(data) {
					if (channel !== undefined) {
						if (item.read !== read) {
							if (read == true)
								channel.unread -= 1;
							else
								channel.unread += 1;
						}
					}
					item.read = read;
				})
				.error(function(error) {
					console.log(error);
				});
		};
		$scope.createChannel = function() {
			Channels.create($scope.channel)
				.success(function(new_channel) {
					$scope.channels.push(new_channel);
				})
				.error(function(error) {
					console.log(error);
				});
		};
		$scope.deleteChannel = function(channel) {
			Channels.delete(channel.id)
				.success(function(res) {
					$scope.items = [];
					$scope.channels = _.reject($scope.channels, {
						id: channel.id
					});
				})
				.error(function(error) {
					console.log(error);
				})
		};
		$scope.refresh = function() {
			Channels.all();
		};
		$scope.loadHtml = function(html) {
			return $sce.trustAsHtml(html);
		}
	}]);