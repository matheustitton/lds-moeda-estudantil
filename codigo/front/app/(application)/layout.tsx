import { SidebarProvider } from "@/components/ui/sidebar"
import "../globals.css"
import ReactQueryProvider from "../_components/QueryClientProvider"
import { AppSidebar } from "../_components/sidebar/sidebar"
 
export default function Layout({ children }: { children: React.ReactNode }) {
  return (
        <>
            <SidebarProvider>
            <AppSidebar />
                <main className="flex-1 w-full overflow-auto">
                    <ReactQueryProvider>
                        {children}
                    </ReactQueryProvider>
                </main>
            </SidebarProvider>
        </>
            
        
  )
}