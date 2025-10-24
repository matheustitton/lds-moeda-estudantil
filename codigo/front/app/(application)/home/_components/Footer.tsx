export default function Footer() {
    return (
        <footer className="bg-[#1E3A8A] text-white py-6 text-center mt-10">
            <p className="text-sm">
                © {new Date().getFullYear()} <span className="font-semibold">EducaCoins</span>. Todos os direitos reservados.
            </p>
        </footer>
    );
}
