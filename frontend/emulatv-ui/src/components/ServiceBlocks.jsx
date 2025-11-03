import "./ServiceBlocks.css"
function ServiceBlocks(props) {
  const imageUrl = `/ui/icons/${props.name}.svg`;

  return (
    <a
      href={props.website}
      target="_blank"
      rel="noopener noreferrer"
      className="service-block"
      style={{ backgroundImage: `url(${imageUrl})` }}
    >
      <div className="service-overlay">
        <h1>{props.name}</h1>
      </div>
    </a>
  );
}

export default ServiceBlocks;
export class Service {
    constructor(id,name,website, image){
        this.id = id;
        this.name = name;
        this.website = website;
        this.image = image;
    }
};