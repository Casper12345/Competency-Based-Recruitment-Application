function Radial(endPercent, color, select, radius) {

  //var radius = 80;
  var border = 5;
  var padding = 1;
  var startPercent = 0;

  var twoPi = Math.PI * 2;
  var formatPercent = d3.format('.0%');
  var boxSize = (radius + padding) * 2;

  var count = Math.abs((endPercent - startPercent) / 0.01);
  var step = endPercent < startPercent ? -0.01 : 0.01;

  var arc = d3.arc()
  .startAngle(0)
  .innerRadius(radius)
  .outerRadius(radius - border);

  var parent = d3.select(select);

  var svg = parent.append('svg')
  .attr('width', boxSize)
  .attr('height', boxSize);

  var defs = svg.append('defs');

  var filter = defs.append('filter')
  .attr('id', 'blur');

  filter.append('feGaussianBlur')
  .attr('in', 'SourceGraphic')
  .attr('stdDeviation', '7');

  var g = svg.append('g')
  .attr('transform', 'translate(' + boxSize / 2 + ',' + boxSize / 2 + ')');

  var meter = g.append('g')
  .attr('class', 'progress-meter');

  meter.append('path')
  .attr('class', 'background')
  .attr('fill', '#ccc')
  .attr('fill-opacity', 0.5)
  .attr('d', arc.endAngle(twoPi));

  var foreground = meter.append('path')
  .attr('class', 'foreground')
  .attr('fill', color)
  .attr('fill-opacity', 1)
  .attr('stroke', color)
  .attr('stroke-width', 1)
  .attr('stroke-opacity', 1);
//.attr('filter', 'url(#blur)');

  var front = meter.append('path')
  .attr('class', 'foreground')
  .attr('fill', color)
  .attr('fill-opacity', 1);

  var numberText = meter.append('text')
  .attr('fill', '#00d4ff')
  .attr('text-anchor', 'middle')
  .attr('dy', '.35em');



  function updateProgress(progress) {
    foreground.attr('d', arc.endAngle(twoPi * progress));
    front.attr('d', arc.endAngle(twoPi * progress));
    numberText.text(formatPercent(progress));
  }

  var progress = startPercent;

  this.run = function () {
    function loops() {
      updateProgress(progress);

      if (count > 0) {
        count--;
        progress += step;
        setTimeout(loops, 20);
      }
    }

    loops();
  };

  updateProgress(0);

}

var colors = {
  'blue': '#032EF7',
  'pink': '#E1499A',
  'yellow': '#f0ff08',
  'green': '#47e495'
};

var divs = {
  'c1': 'div#content',
  'c2': 'div#content2',
  'c3': 'div#content3'
};

var percentages = {
  'per1': document.getElementById("per1").innerHTML * 0.01,
  'per2': document.getElementById("per2").innerHTML * 0.01,
  'per3': document.getElementById("per3").innerHTML * 0.01
};

var radial1 = new Radial(percentages.per1, colors.blue, divs.c1, 50);
var radial2 = new Radial(percentages.per2, colors.pink, divs.c2, 50);
var radial3 = new Radial(percentages.per3, colors.green, divs.c3, 50);

radial1.run();
radial2.run();
radial3.run();