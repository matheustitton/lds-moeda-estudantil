import { SidebarProvider } from "@/components/ui/sidebar"
import "../globals.css"
import ReactQueryProvider from "../_components/QueryClientProvider"
import { AppSidebar } from "../_components/sidebar/sidebar"
import { UsuarioContextProvider } from "@/contexts/user.context"
import Navbar from "./home/_components/NavBar"
import Footer from "./home/_components/Footer"
 
export default function Layout({ children }: { children: React.ReactNode }) {
  return (
        <>
            <UsuarioContextProvider>
                <SidebarProvider>
                <div className="flex h-screen w-screen">
                    <AppSidebar />
                    <main className="flex-1 flex flex-col">
                        <Navbar />

                        <ReactQueryProvider>
                        <div className="flex-1">
                            {children}
                        </div>
                        </ReactQueryProvider>
                        {/* <Footer /> */}
                    </main>
                </div>
                </SidebarProvider>
            </UsuarioContextProvider>
        </>
  )
}