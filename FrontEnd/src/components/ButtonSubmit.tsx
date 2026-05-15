type ButtonProps = {
  text: string;
  color: keyof typeof colors;
};

const colors = {
  purple: "bg-[#864DBF] hover:bg-[#633295]",
  orange: "bg-[#ee9467] hover:bg-[#c9673a]",
  blue: "bg-blue-500 hover:bg-blue-600",
};

export default function ButtonSubmit({ text, color}: ButtonProps) {
  return (
    <button
      type="submit"
      className={`w-full text-black font-semibold py-2 rounded-lg transition-all duration-200 active:scale-95 ${colors[color]}`}
    >
      {text}
    </button>
  );
}
