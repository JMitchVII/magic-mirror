// Made with Zdog
let illo = new Zdog.Illustration({
  element: ".zdog-canvas",
  dragRotate: true,
  onDragStart: function() {
    isSpinning = false;
    smile.visible = false;
    surpriseGroup.visible = true;
    faceGroup.visible = false;
  },
  onDragEnd: function() {
    isSpinning = true;
    smile.visible = true;
    surpriseGroup.visible = false;
    faceGroup.visible = true;
  },
  zoom: 16,
  rotate: { x: Zdog.TAU/64, z: Zdog.TAU/64 },
});

let skin = new Zdog.Shape({
  addTo: illo,
  path: [
    { x: -10,   y: 2, z: 2 },
    { x: -4,    y: 0, z: 2 },
    { x: 4,     y: 0, z: 2 },
    { x: 10,    y: 2, z: 2 },
    { x: 9,     y: 4, z: 1.8 },
    { x: 3.5,   y: 2, z: 1.8 },
    { x: -3.5,  y: 2, z: 1.8 },
    { x: -9,    y: 4, z: 1.8 }   
  ],
  stroke: 1 / illo.zoom,
  fill: true,
  color: "#7AB13E",
  translate: { y: -10 },
});

let skinBack = skin.copy({
  rotate: { y: Zdog.TAU/2 },
})

let skinTopLeft = new Zdog.Shape({
  addTo: skin,
  path: [
    { x: -10, y: 2, z: -2 },
    { x: -4,  y: 0, z: -2 },
    { x: -4,  y: 0, z: 2 },
    { x: -10, y: 2, z: 2 },
  ],
  closed: true,
  fill: true,
  color: "#7AB13E",
  stroke: 1 / illo.zoom,
});

let skinTopRight = skinTopLeft.copy({
  rotate: { y: Zdog.TAU/2 },
})

let skinTopCentre = new Zdog.Shape({
  addTo: skin,
  path: [
    { x: -4, y: 0, z: -2 },
    { x: 4,  y: 0, z: -2 },
    { x: 4,  y: 0, z: 2 },
    { x: -4, y: 0, z: 2 },
  ],
  closed: true,
  fill: true,
  color: "#7AB13E",
  stroke: 1 / illo.zoom,
});

let skinEnd = new Zdog.Shape({
  addTo: skin,
  path: [
    { x: 10, y: 2, z: 2 },
    { x: 9,  y: 4, z: 1.8 },  
    { x: 9,  y: 4, z: -1.8 },
    { x: 10, y: 2, z: -2 },
  ],
  stroke: 1 / illo.zoom,
  closed: true,
  fill: true,
  color: "#7AB13E",
});

skinEnd.copy({
  rotate: { y: Zdog.TAU/2 },
})

let rind = new Zdog.Shape({
  addTo: skin,
  path: [
    { x: -9,   y: 4, z: 1.8 },
    { x: -3.5, y: 2, z: 1.8 },
    { x: 3.5,  y: 2, z: 1.8 },
    { x: 9,    y: 4, z: 1.8 },
    { x: 8.5,  y: 5, z: 1.8 },
    { x: 3.5,  y: 3, z: 1.8 },
    { x: -3.5, y: 3, z: 1.8 },
    { x: -8.5, y: 5, z: 1.8 },
    { x: -9,   y: 4, z: 1.8 }
  ],
  closed:true,
  fill: true,
  color: "#EEEB9A",
  stroke: 1 / illo.zoom,
});

let rindEndLeft = new Zdog.Shape({
  addTo: skin,
  path: [
    { x: -8.5, y: 5, z: 1.8 },
    { x: -9,   y: 4, z: 1.8 },
    { x: -9,   y: 4, z: -1.8 },
    { x: -8.5, y: 5, z: -1.8 },    
  ],
  closed:true,
  fill: true,
  color: "#EEEB9A",
  stroke: 1 / illo.zoom,
});

let rindEndRight =  rindEndLeft.copy({
  rotate: { y: Zdog.TAU/2 },
})

let rindBack = rind.copy({
  rotate: { y: Zdog.TAU/2 },
})

let flesh = new Zdog.Shape({
  addTo: rind,
  path: [
    { x: -8.5,  y: 5, z: 1.8 },
    { x: -3.5,  y: 3, z: 1.8 },
    { x: 3.5,   y: 3, z: 1.8 },
    { x: 8.5,   y: 5, z: 1.8 },
    { x: 0, y: 22 },
  ],
  fill: true,
  color: "#FF4846",
  stroke: 1 / illo.zoom,
});

let fleshEndLeft = new Zdog.Shape({
  addTo: rind,
  path: [
    { x: -8.5,  y: 5, z: 1.8 },
    { x: -8.5,  y: 5, z: -1.8 },
    { x: 0, y: 22 },
  ],
  closed: true,
  fill: true,
  color: "#FF4846",
  stroke: 1 / illo.zoom,
});

let fleshEndRight = fleshEndLeft.copy({
  rotate: { y: Zdog.TAU/2 },
})

let fleshBack = flesh.copy({
  rotate: { y: Zdog.TAU/2 },
})

var surpriseGroup = new Zdog.Group({
  addTo: flesh,
  translate: { y: 7, z: 1.8 },
  visible: false,
});

let surprise = new Zdog.Ellipse({
  addTo: surpriseGroup,
  diameter: 1.5,
  translate: { x: 0, y: 0.5, z: 0.2 },
  rotate: { z: Zdog.TAU/4 },
  stroke: 0.5,
  color: '#20201E',
});

let surpriseEyeLeft = new Zdog.Shape({
  addTo: surpriseGroup,
  path: [
    { x: -3, y: -0.5 },
    { x: -2, y: 0 },
    { x: -3, y: 0.5 }, 
  ],
  closed: false,
  stroke: 0.5,
  color: '#20201E',
});

let surpriseEyeRight = surpriseEyeLeft.copy({
  rotate: { y: Zdog.TAU/2 },
})

var faceGroup = new Zdog.Group({
  addTo: flesh,
  translate: { y: 7, z: 1.8 },
  visible: true,
});

let eyeLeft = new Zdog.Shape({
  addTo: faceGroup,
  path: [
    { x: -2.5 },
  ],
  stroke: 1.5,
  color: '#20201E',
});

let sparkleLeft = new Zdog.Shape({
  addTo: faceGroup,
  path: [
    { x: -2.8, y: -0.3 }, // start at 1st point
  ],
  stroke: 0.5,
  color: '#FFF',
});

let eyeRight = eyeLeft.copy({
  rotate: { y: Zdog.TAU/2 },
})

let sparkleRight = sparkleLeft.copy({
  translate: { x: 5 },
})

let smile = new Zdog.Ellipse({
  addTo: faceGroup,
  diameter: 1.5,
  quarters: 2,
  translate: { x: 0, y: 0.5, z: 0.2 },
  rotate: { z: Zdog.TAU/4 },
  stroke: 0.5,
  color: '#20201E',
});

var seedGroup = new Zdog.Group({
  addTo: flesh,
  translate: { y: 7, z: 1.8 },
  rotate: { x: -Zdog.TAU/80 },
});

let seedFront = new Zdog.Shape({
  addTo: seedGroup,
  path: [
    { x: 0, y: 0 },   // start
    { bezier: [
      { x:  -0.5, y: 0 }, // start control point
      { x:  -0.5, y:  0.75 }, // end control point
      { x:  0, y:  1.5 }, // end point
    ]},
    { x: 0, y: 1.5 },   // start
    { bezier: [
      { x:  0.5, y: 0.75 }, // start control point
      { x:  0.5, y:  0 }, // end control point
      { x:  0, y:  0 }, // end point
    ]},
  ],
  stroke: 0.2,
  fill: true,
  rotate: { z: Zdog.TAU/2 },
  color: '#20201E',
  translate: { x: 4, y: 3, }
});

seedFront.copy({
  translate: { x: -4, y: 3 }
})


seedFront.copy({
  translate: { x: -2, y: 7 }
})

seedFront.copy({
  translate: { x: 2, y: 7 }
})

seedFront.copy({
  translate: { x: 0, y: 11 }
})

var seedGroup = new Zdog.Group({
  addTo: flesh,
  translate: { y: 7, z: -1.8 },
  rotate: { x: Zdog.TAU/80 },
});

let seedBack = new Zdog.Shape({
  addTo: seedGroup,
  path: [
    { x: 0, y: 0 },   // start
    { bezier: [
      { x:  -0.5, y: 0 }, // start control point
      { x:  -0.5, y:  0.75 }, // end control point
      { x:  0, y:  1.5 }, // end point
    ]},
    { x: 0, y: 1.5 },   // start
    { bezier: [
      { x:  0.5, y: 0.75 }, // start control point
      { x:  0.5, y:  0 }, // end control point
      { x:  0, y:  0 }, // end point
    ]},
  ],
  stroke: 0.2,
  fill: true,
  rotate: { z: Zdog.TAU/2 },
  color: '#20201E',
  translate: { x: 4, y: 2, }
});

seedBack.copy({
  translate: { x: 0, y: -1 }
})

seedBack.copy({
  translate: { x: -4, y: 2 }
})

seedBack.copy({
  translate: { x: -2, y: 5.5 }
})

seedBack.copy({
  translate: { x: 2, y: 5.5 }
})

seedBack.copy({
  translate: { x: 0, y: 9 }
})

let ticker = 0;
let cycleCount = 180;
let isSpinning = true;

function animate() {
  let progress = ticker / cycleCount;
  let tween = Zdog.easeInOut( progress % 1, 3 );
  
  if (isSpinning){
    illo.rotate.y = tween * Zdog.TAU;
  }
  
  ticker++;

  illo.updateRenderGraph();
  requestAnimationFrame( animate );
}
animate();