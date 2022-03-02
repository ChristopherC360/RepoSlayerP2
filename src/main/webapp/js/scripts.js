const charts = document.querySelectorAll(".chart");

// charts.forEach(function (chart) {
    const ctx = chart.getContext('2d');
    const myChartPie = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: ["rice", "yam", "tomato", "potato",
        "beans", "maize", "oil"],
        datasets: [{
          label: 'food Items',
          backgroundColor: 'rgba(161, 198, 247, 1)',
          borderColor: 'rgb(47, 128, 237)',
          data: [30, 40, 20, 50, 80, 90, 20],
        }]
      },
    });
// });


      const ctx = chart.getContext('2d');
      const myChartPie = new Chart(ctx, {
        type: 'pie',
        data: {
          labels: ["rice", "yam", "tomato", "potato",
          "beans", "maize", "oil"],
          datasets: [{
            label: 'food Items',
            backgroundColor: 'rgba(161, 198, 247, 1)',
            borderColor: 'rgb(47, 128, 237)',
            data: [30, 40, 20, 50, 80, 90, 20],
          }]
        },
      });


$(document).ready(function () {
  $(".data-table").each(function (_, table) {
    $(table).DataTable();
  });
});
