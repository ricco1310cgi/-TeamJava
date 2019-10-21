var ctx = document.getElementById('firstChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
				type: 'line',
				label: 'Dataset 1',
				borderColor: 'rgb(255, 99, 132)',
				borderWidth: 2,
                fill: false,
                lineTension: 0,
				data: [
					12, 19, 3, 5, 2, 3
				]
			}, {
				type: 'bar',
				label: 'Dataset 2',
				backgroundColor: 'rgb(75, 192, 192)',
				data: [
					15, 5, 13, 8, 12, 13
				],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        },
        title: {
            display: true,
            position: 'top',
            text: 'Jaaroverzicht'
        },
    }
});

var ctx = document.getElementById('secondChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [6, 8, 15, 5, 8, 16],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        },
        title: {
            display: true,
            position: 'top',
            text: 'Maandoverzicht'
        }
    }
});