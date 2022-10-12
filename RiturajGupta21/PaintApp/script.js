const loadStorageButton = document.getElementById('load-storage');
const saveStorageButton = document.getElementById('save-storage');
const clearStorageButton = document.getElementById('clear-storage');
const clearCanvasButton = document.getElementById('clear-canvas');
const activeToolElement = document.getElementById('active-tool');
const brushColorButton = document.getElementById('brush-color');
const brushIcon = document.getElementById('brush');
const brushSize = document.getElementById('brush-size');
const brushSlider = document.getElementById('brush-slider');
const bucketColorButton = document.getElementById('bucket-color');
const eraser = document.getElementById('eraser');
const downloadButton = document.getElementById('download');
const canvas = document.createElement('canvas');
const { body } = document;
const timeOut = 1000;
canvas.id = 'canvas';
const context = canvas.getContext('2d');
let currentSize = 12;
let brushColor = '#FFFFFF';
let currentBgColor = '#000000';
let isEraser = false;
let isMouseDown = false;
let drawnArray = [];

// Brush Size Formatting
function displayBrushSize() {
    if (brushSlider.value < 12) brushSize.textContent = `0${brushSlider.value}`;
    else brushSize.textContent = brushSlider.value;
}

// Brush Size
brushSlider.addEventListener('change', () => {
    currentSize = brushSlider.value;
    displayBrushSize();
});

// Brush Color
brushColorButton.addEventListener('change', () => {
    isEraser = false;
    currentBgColor = `#${brushColorButton.value}`;
});

// Background Color
bucketColorButton.addEventListener('change', () => {
    brushColor = `#${bucketColorButton.value}`;
    createCanvas();
    restoreCanvas();
});

// Eraser
eraser.addEventListener('click', () => {
    isEraser = true;
    brushIcon.style.color = 'white';
    eraser.style.color = 'black';
    activeToolElement.textContent = 'Eraser';
    currentBgColor = brushColor;
    currentSize = 52;
});

// Switch to Brush
function switchToBrush() {
    isEraser = false;
    activeToolElement.textContent = 'Brush';
    brushIcon.style.color = 'black';
    eraser.style.color = 'white';
    currentBgColor = `#${brushColorButton.value}`;
    currentSize = 12;
    brushSlider.value = 12;
    displayBrushSize();
}

function brushTimeSetTimeout(ms) {
    setTimeout(switchToBrush, ms);
}

// Create Canvas
function createCanvas() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight - 50;
    context.fillStyle = brushColor;
    context.fillRect(0, 0, canvas.width, canvas.height);
    body.appendChild(canvas);
    switchToBrush();
}

// Clear Canvas
clearCanvasButton.addEventListener('click', () => {
    createCanvas();
    drawnArray = [];
    // Active Tool
    activeToolElement.textContent = 'Canvas Cleared';
    brushTimeSetTimeout(timeOut);
});

// Store in DrawnArray
function restoreCanvas() {
    for (let i = 1; i < drawnArray.length; i++) {
        context.beginPath();
        context.moveTo(drawnArray[i - 1].x, drawnArray[i - 1].y);
        context.lineWidth = drawnArray[i].size;
        context.lineCap = 'round';
        if (drawnArray[i].eraser) {
            context.strokeStyle = brushColor;
        } else {
            context.strokeStyle = drawnArray[i].color;
        }
        context.lineTo(drawnArray[i].x, drawnArray[i].y);
        context.stroke();
    }
}

// Store Lines Drawn
function storeDrawn(x, y, size, color, erase) {
    const line = {
        x,
        y,
        size,
        color,
        erase,
    };
    console.log(line);
    drawnArray.push(line);
}

// Get Mouse Position
function getMousePosition(event) {
    const boundaries = canvas.getBoundingClientRect();
    return {
        x: event.clientX - boundaries.left,
        y: event.clientY - boundaries.top,
    };
}

// Mouse Down
canvas.addEventListener('mousedown', (event) => {
    isMouseDown = true;
    const currentPosition = getMousePosition(event);
    context.moveTo(currentPosition.x, currentPosition.y);
    context.beginPath();
    context.lineWidth = currentSize;
    context.lineCap = 'round';
    context.strokeStyle = currentBgColor;
});

// Mouse Move
canvas.addEventListener('mousemove', (event) => {
    if (isMouseDown) {
        const currentPosition = getMousePosition(event);
        context.lineTo(currentPosition.x, currentPosition.y);
        context.stroke();
        storeDrawn(
            currentPosition.x,
            currentPosition.y,
            currentSize,
            currentBgColor,
            isEraser,
        );
    } else {
        storeDrawn(undefined);
    }
});

// Mouse Up
canvas.addEventListener('mouseup', () => {
    isMouseDown = false;
});

// Save in Local Storage
saveStorageButton.addEventListener('click', () => {
    localStorage.setItem('savedCanvas', JSON.stringify(drawnArray));
    activeToolElement.textContent = 'Canvas Saved';
    brushTimeSetTimeout(timeOut);
});

// Load from Local Storage
loadStorageButton.addEventListener('click', () => {
    if (localStorage.getItem('savedCanvas')) {
        drawnArray = JSON.parse(localStorage.savedCanvas);
        restoreCanvas();
        activeToolElement.textContent = 'Canvas Loaded';
        brushTimeSetTimeout(timeOut);
    } else {
        activeToolElement.textContent = 'No Canvas Found';
        brushTimeSetTimeout(timeOut);
    }

});

// Clear Local Storage
clearStorageButton.addEventListener('click', () => {
    localStorage.removeItem('savedCanvas');
    activeToolElement.textContent = 'Local Storage Cleared';
    brushTimeSetTimeout(timeOut);
});

// Download Image
downloadButton.addEventListener('click', () => {
    downloadButton.href = canvas.toDataURL('image/jpeg', 1);
    downloadButton.download = 'Draw.jpg';
    activeToolElement.textContent = 'Image Saved';
    brushTimeSetTimeout(timeOut);
});

// Event Listener
brushIcon.addEventListener('click', switchToBrush);

// On Load
createCanvas();