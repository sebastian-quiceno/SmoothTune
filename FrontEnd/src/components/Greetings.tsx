import defaultUser from "/defaultUser.png";

type UserButtonProps = {
  image: string;
  username: string;
};

const Greetings = ({ image, username }: UserButtonProps) => {
  return (
    <div className="flex flex-row items-center gap-2">
      <img
        src={image === "" ? defaultUser : image}
        alt="Vista previa del reproductor de música"
        className="w-[70px]"
      />
      <div className="flex flex-col text-white text-xl">
        <span className="text-white/60 text-2xl">Bienvenido</span>
        <span className="font-bold text-2xl">{username}</span>
      </div>
    </div>
  );	
};

export default Greetings;
