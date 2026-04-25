import portraitNotFound from "/portraitNotFound.jpg";

type SongCardProps = {
  songName: string;
  autor: string;
  img: string;
};

const SongCard = ({ songName, autor, img }: SongCardProps) => {
  return (
    <div className="flex flex-col text-white max-w-[200px]">
      <img
        className="w-[200px] rounded-xl hover:shadow-sm"
        src={img === "" ? portraitNotFound : img}
        alt="Portada de la canción"
      />
      <span className="text-xl font-bold">{songName}</span>
      <span className="text-white/70 text-xl">{autor}</span>
    </div>
  );
};

export default SongCard;
