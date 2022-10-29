import React from "react";
import { useState } from "react";
import ReactMarkdown from 'react-markdown'
const style={height:"20vw", width:"50vw", backgroundColor: "lightblue"};
function Editor() {
    const [textEditor, setTextEditor] = useState ();
    return <>
        <div>
            <textarea style={style}
            onChange={(e) => setTextEditor(e.target.value)}></textarea>
            <div style={style}>
                <ReactMarkdown>{textEditor}</ReactMarkdown>
                </div>
        </div>
    </>;
}

export default Editor;