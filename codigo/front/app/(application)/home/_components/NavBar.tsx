import { Button } from "@/components/ui/button";
import Image from "next/image";

export default function Navbar() {
    return (
        <nav className="w-full flex items-center justify-between px-8 py-4 bg-white shadow-md">
            <div className="flex items-center gap-3">
                <Image
                    src="/logo.png"
                    alt="EducaCoins Logo"
                    width={50}
                    height={50}
                    className="rounded-full opacity-80"
                />
                <h1 className="text-2xl font-bold text-[#1E3A8A]">EducaCoins</h1>
            </div>

            <ul className="hidden md:flex gap-8 text-gray-700 font-medium">
                <li className="hover:text-[#1E3A8A] cursor-pointer transition-colors">
                    Início
                </li>
                <li className="hover:text-[#1E3A8A] cursor-pointer transition-colors">
                    Sobre
                </li>
                <li className="hover:text-[#1E3A8A] cursor-pointer transition-colors">
                    Vantagens
                </li>
                <li className="hover:text-[#1E3A8A] cursor-pointer transition-colors">
                    Contato
                </li>
            </ul>

            <Button className="bg-[#7B1E3A] text-white px-5 py-2 rounded-full font-semibold hover:opacity-70 transition">
                Entrar
            </Button>
        </nav>
    );
}
