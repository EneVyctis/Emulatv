function ServiceBlocks(props) {
  return (
    <div>
    <h1>{props.name}</h1>
    <a href={props.website}>button</a>
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