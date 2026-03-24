import type { LucideIcon } from "lucide-react";

type CardProps = {
  icon: LucideIcon;
  text: string;
  image: string;
  alt: string;
  color: boolean;
};

export default function Card({ icon: Icon, text, image, alt, color }: CardProps) {
  return (
    <div className={` ${color ? "bg-[#864DBF]" : "bg-[#58BDDE]"} rounded-xl shadow-md p-6 flex flex-col items-center text-center transition-transform duration-300 hover:scale-105 gap-2`}>
      <Icon className="text-white w-6 h-6"/>
      <h3 className="text-white font-bold">{text}</h3>
			<img
        src={image}
        alt={alt}
        className="w-60 h-60 object-cover rounded-lg mb-4"
        loading="lazy"
      />

    </div>
  );
}

