import { useState, useEffect } from 'react';

export default function ManageServices() {
    const [services, setServices] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchServices = async () => {
            try {
                const response = await fetch('/api/list/services', {
                    method: 'GET',
                });
                if (!response.ok) {
                    throw new Error('Error while fetching services');
                }
                const data = await response.json();
                setServices(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchServices();
    }, []);

    if (loading) {
        return <p>Chargement en cours...</p>;
    }

    if (error) {
        return <p>Erreur : {error}</p>;
    }


    const handleDelete = async(id) => {
        if(!id) {
            setError('Missing parameter');
            return;
        }

        if (!window.confirm('Do you really want to delete ?')) return;

        try{
            const response = await fetch(`/api/admin/delete-service/${id}`, {
                method: 'DELETE',
                body: id,
            });

            if (!response.ok){
                throw new Error('Error when deleting the service');
            }

            console.log('Service deleted !');

            setError('');

        } catch(err) {
            setError(err.message);
        }
    };
    
    return (
        <ul>
            {services.map((service) => (
                <li key={service.id}>
                    <strong>ID:</strong> {service.id}, <strong>Name:</strong> {service.name} <button onClick={() => handleDelete(service.id)}>Delete</button>
                </li>
            ))}
        </ul>
    );
}
