function ServiceBlocks(props) {
  return (
    <div>
    <h1>{props.name}</h1>
    <p>{props.id}</p>
    <p>{props.website}</p>
    </div>
  );
}

export default ServiceBlocks;
export class Service {
    constructor(id,name,website){
        this.id = id;
        this.name = name;
        this.website = website;
    }
}