import { useState } from "react";

function DeleteServiceForm(){
    const [name, setName] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async(e) => {
        e.preventDefault();

        if(!name) {
            setError('Missing parameter');
            return;
        }

        try{
            const response = await fetch('/api/admin/delete-service', {
                method: 'POST',
                body: name,
            });

            if (!response.ok){
                throw new Error('Error when deleting the service');
            }

            console.log('Service deleted !');

            setName('');
            setError('');

        } catch(err) {
            console.error(err);
            setError('Problem encountered while deleting the service')
        }
    };

    return(
        <form onSubmit={handleSubmit}>
        <div>
            <label>Service Name</label>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        </div>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <button type="submit">Delete service</button>
        </form>
    );
}

export default DeleteServiceForm;