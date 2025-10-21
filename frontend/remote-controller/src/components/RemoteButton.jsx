import React from "react";

function RemoteButton(props) {
    const handleClick = async () => {
        try {
            const response = await fetch(`${props.endpoint}`);
            console.log("OK:", response.status);
        }   catch (error) {
            console.error("error", error);
        }
    };

    return(
        <button
        onClick={handleClick}
        className={props.className}
        >
        {props.label}
        </button>
    );
}

export default RemoteButton;