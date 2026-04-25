import defaultUser from "/defaultUser.png";

type UserButtonProps = {
	image: string,
	username: string
}

const UserButton = ({image, username}:UserButtonProps) => {
  return (
    <div className="flex flex-row items-center gap-2">
      <img
        src={image === "" ? defaultUser : image}
        alt="Vista previa del reproductor de música"
        className="w-[50px]"
      />

      <span className="text-white/50 text-xl">{username}</span>
    </div>
  );
};

export default UserButton;
