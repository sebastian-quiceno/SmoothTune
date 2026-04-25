
type ButtonProps = {
  text: string;
  Icon: React.ElementType;
  direccion: string;
  onclick: (direccion: string) => void; //Revisar
  isSelect: boolean;
};

export default function Button({
  text,
  Icon,
  direccion,
  onclick,
  isSelect,
}: ButtonProps) {
  return (
    <button
      className={`w-52 h-12 p-2 ${isSelect ? "bg-[#4E3E81]" : "bg-[#4E3E8130]"} text-white flex flex-row items-center justify-start hover:scale-105 gap-2 rounded-lg transition-all duration-200`}
      onClick={() => {
        onclick(direccion);
      }}
    >
      <Icon />
      <span>{text}</span>
      
    </button>
  );
}
