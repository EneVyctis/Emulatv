import { useState } from "react";

function AddServiceForm(){
    const [name, setName] = useState('');
    const [website, setWebsite] = useState('');
    const [icon, setIcon] = useState('');
    const [error, setError] = useState('');

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file && file.type === "image/svg+xml") {
            setIcon(file);
            setError('');
        } else {
            setIcon(null);
            setError('Please provide a valid svg file.');
        }
    };

    const handleSubmit = async(e) => {
        e.preventDefault();

        if(!name | !website | !icon) {
            setError('Missing parameters');
            return;
        }

        const fileName = `${name.replace(/\s+/g, "_")}.svg`;

        const formData = new FormData();
        formData.append("icon", icon, fileName);
        formData.append("name", name);
        formData.append("website", website);

        try{
            const response = await fetch('/api/admin/upload-service', {
                method: 'POST',
                body: formData,
            });

            if (!response.ok){
                throw new Error('Error when adding the service');
            }

            console.log('Service added !');

            setName('');
            setWebsite('');
            setIcon(null);
            setError('');

        } catch(err) {
            console.error(err);
            setError('Problem encountered while adding the service')
        }
    };

    return(
        <form onSubmit={handleSubmit}>
        <div>
            <label>Service Name</label>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div>
            <label>Website</label>
            <input type="text" value={website} onChange={(e) => setWebsite(e.target.value)} />
        </div>
        <div>
            <label>Icon (SVG)</label>
            <input type="file" accept=".svg" onChange={handleFileChange} />
        </div>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <button type="submit">Add service</button>
        </form>
    );
}

export default AddServiceForm;