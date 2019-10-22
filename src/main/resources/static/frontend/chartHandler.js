var calcObject = {
    beginday: -1,
    calculation: -1,
    period: "-1",
    value: "-1"
}

var currentTime = moment();

var lastTenYears = [
    currentTime.format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
    currentTime.subtract(1, 'year').format("YYYY"),
]

lastTenYears.reverse();

var lastTenYearsCalculationsTemp = [];
var lastTenYearsCalculationsGas = [];

currentTime = moment();

var previous12Months = [
    currentTime.format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
    currentTime.subtract(1, 'months').format("MMM YY"),
]
previous12Months.reverse();

currentTime = moment();
currentTime = currentTime.startOf('day');

var daysSinceBeginMonth = [

]

for (let i = 0; i < 31; i++) {
    if (currentTime.date() == 1) {
        daysSinceBeginMonth[i] = currentTime.format("DD");
        break;
    }
    daysSinceBeginMonth[i] = currentTime.format("DD");
    currentTime.subtract(1, 'day');
}

daysSinceBeginMonth.reverse();

currentTime = moment();

for (let i = 0; i < daysSinceBeginMonth.length; i++) {
    console.log(currentTime.subtract(1, 'days').unix());

}

var latestDayRequest = currentTime.subtract(daysSinceBeginMonth.length, 'days').unix();

currentTime = moment();


var xhttpYear = new XMLHttpRequest();
xhttpYear.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
        calcObject = JSON.parse(this.responseText);
        for (let i = 0; i < calcObject.length; i++) {
            lastTenYearsCalculationsTemp[i] = calcObject[i].calculation;
            if (lastTenYearsCalculationsTemp[i] < -300) {
                lastTenYearsCalculationsTemp[i] = 0;
            }
        }

        var xhttpYear2 = new XMLHttpRequest();

        xhttpYear2.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                calcObject = JSON.parse(this.responseText);
                for (let i = 0; i < calcObject.length; i++) {
                    lastTenYearsCalculationsGas[i] = calcObject[i].calculation;
                    if (lastTenYearsCalculationsGas[i] < -300) {
                        lastTenYearsCalculationsGas[i] = 0;
                    }
                }
                generateYearsChart(lastTenYearsCalculationsTemp, lastTenYearsCalculationsGas);
            }
        };
        xhttpYear2.open("GET", "http://localhost:8082/api/boiler/calculation/1256047682/1571668232/year/gas");
        xhttpYear2.setRequestHeader("Content-type", "application/json");
        xhttpYear2.send();
    }
};
xhttpYear.open("GET", "http://localhost:8082/api/boiler/calculation/1256047682/1571668232/year/temperature");
xhttpYear.setRequestHeader("Content-type", "application/json");
xhttpYear.send();

generateMonthsChart();

function generateYearsChart(dataToFill, dataToFill2) {
    var ctx = document.getElementById('yearChart').getContext('2d');
    var myChartYear = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: lastTenYears,
            datasets: [{
                type: 'line',
                label: 'Temperatuur',
                borderColor: 'rgb(75, 75, 132)',
                borderWidth: 2,
                fill: false,
                lineTension: 0,
                data: dataToFill,
                yAxisID: 'leftYear'
            }, {
                type: 'bar',
                label: 'Verbruik',
                backgroundColor: 'rgb(75, 192, 75)',
                data: dataToFill2,
                yAxisID: 'rightYear',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        padding: 1,
                        beginAtZero: true
                    },
                    type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                    display: true,
                    position: 'left',
                    id: 'leftYear'
                }, {
                    ticks: {
                        padding: 1,
                        beginAtZero: true
                    },
                    type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                    display: true,
                    position: 'right',
                    id: 'rightYear',

                    // grid line settings
                    gridLines: {
                        drawOnChartArea: false, // only want the grid lines for one axis to show up
                    },
                }],
            },
            title: {
                display: true,
                position: 'top',
                text: 'Jaaroverzicht'
            }
        }
    });
}

function generateMonthsChart(dataToFill) {
    var ctx = document.getElementById('monthChart').getContext('2d');
    var myChartMonth = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: previous12Months,
            datasets: [{
                type: 'line',
                label: 'Temperatuur',
                borderColor: 'rgb(255, 99, 132)',
                borderWidth: 2,
                fill: false,
                lineTension: 0,
                data: [
                    15.21, 19.23, 21.52, 22.10, 18.60, 18.20, 18.20, 20.65, 20.12, 21.08, 18.60, 18.26
                ],
                yAxisID: 'leftMonth'
            }, {
                type: 'bar',
                label: 'Verbruik',
                backgroundColor: 'rgb(75, 192, 192)',
                data: [
                    45, 63, 78, 84, 67, 67, 66, 67, 75, 74, 65, 64 
                ],
                borderWidth: 1,
                yAxisID: 'rightMonth'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        padding: 1,
                        beginAtZero: true
                    },
                    type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                    display: true,
                    position: 'left',
                    id: 'leftMonth',
                    padding: 1
                }, {
                    ticks: {
                        padding: 1,
                        beginAtZero: true
                    },
                    type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
                    display: true,
                    position: 'right',
                    id: 'rightMonth',
                    padding: 1,

                    // grid line settings
                    gridLines: {
                        drawOnChartArea: false, // only want the grid lines for one axis to show up
                    },
                }],
            },
            title: {
                display: true,
                position: 'top',
                text: 'Maandoverzicht'
            },
        }
    });
}

// var ctx = document.getElementById('dayChart').getContext('2d');
// var myChartDay = new Chart(ctx, {
//     type: 'bar',
//     data: {
//         labels: daysSinceBeginMonth,
//         datasets: [{
//             type: 'line',
//             label: 'Temperatuur',
//             borderColor: 'rgb(75, 75, 132)',
//             borderWidth: 2,
//             fill: false,
//             lineTension: 0,
//             data: [
//                 12, 19, 3, 5, 2, 3, 18, 6, 15, 5, 8, 15, 12, 17, 5, 8, 2, 6, 8, 13, 15, 6, 8, 10
//             ],
//             yAxisID: 'leftDay'
//         }, {
//             type: 'bar',
//             label: 'Verbruik',
//             backgroundColor: 'rgb(75, 192, 75)',
//             data: [
//                 15, 5, 13, 8, 12, 13, 18, 13, 16, 8, 9, 2, 16, 12, 10, 18, 6, 8, 3, 1, 12, 18, 8, 13
//             ],
//             yAxisID: 'rightDay',
//             borderWidth: 1
//         }]
//     },
//     options: {
//         scales: {
//             yAxes: [{
//                 ticks: {
//                     padding: 1,
//                     beginAtZero: true
//                 },
//                 type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
//                 display: true,
//                 position: 'left',
//                 id: 'leftDay',
//             }, {
//                 ticks: {
//                     padding: 1,
//                     beginAtZero: true
//                 },
//                 type: 'linear', // only linear but allow scale type registration. This allows extensions to exist solely for log scale for instance
//                 display: true,
//                 position: 'right',
//                 id: 'rightDay',

//                 // grid line settings
//                 gridLines: {
//                     drawOnChartArea: false, // only want the grid lines for one axis to show up
//                 },
//             }],
//         },
//         title: {
//             display: true,
//             position: 'top',
//             text: 'Dagoverzicht'
//         }
//     }
// });
