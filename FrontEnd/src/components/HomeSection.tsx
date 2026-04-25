import SearchBar from '../components/SearchBar'
import UserButton from '../components/UserButton'
import Greetings from '../components/Greetings'
import UploadedSongs from '../components/UploadedSongs'
import SongCard from '../components/SongCard'

type HomeSectionProps = {
    
}

const HomeSection = () => {
  return (
    <section className="w-full">
      <header className="flex flex-row justify-between p-5 w-full">
        <SearchBar
          value={search}
          onChange={setSearch}
          results={canciones}
          loading={false}
          placeholder="Buscar Canciones..."
          keyExtractor={(p) => p}
          renderItem={(p) => p}
          onSelect={(item) => {
            console.log("Profesor seleccionado:", item);
          }}
        />
        <UserButton image="" username="User Name" />
      </header>
      <div className="flex flex-row justify-between">
        <Greetings image="" username="User Name" />
        <UploadedSongs uploadedSongs={25} />
      </div>
      <hr className="mx-5 my-2 border-[#191527] border-2" />

      <div className="text-white font-bold">
        <span className="text-4xl">¿Que vas a escuchar hoy?</span>
      </div>

      <SongCard autor="PinkFloid" img="" songName="Wish you where here" />
    </section>
  );
};
