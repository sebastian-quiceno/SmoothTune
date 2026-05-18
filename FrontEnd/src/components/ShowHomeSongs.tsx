import SongCard from '../components/SongCard'

export const ShowHomeSongs = () => {
  return (
    <div className="grid grid-cols-5 gap-4 mt-5">
      <SongCard autor="PinkFloid" img="" songName="Wish you where here" />
      <SongCard autor="PinkFloid" img="" songName="Wish you where here" />
    </div>
  );
};
