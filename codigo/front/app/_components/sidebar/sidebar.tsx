"use client";

import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarHeader,
  SidebarMenu,
  SidebarRail,
  SidebarSeparator,
  SidebarTrigger,
} from "@/components/ui/sidebar";
import Image from "next/image";
import SiderbarItem from "./SidebatItem";
import { SidebarConfigItem } from "./types";
import { RxDashboard } from "react-icons/rx";
import { routes } from "./routes";
import { FiUsers } from "react-icons/fi";
import Sessao from "@/lib/utils/Sessao";
import { useEffect, useState } from "react";
import { Contact, PackageOpen, FileText, Monitor, Calendar, House } from "lucide-react";

export function AppSidebar() {
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    const role = Sessao.buscarRole();
    setIsAdmin(role === "Administrador");
  }, []);

  const mockItems: SidebarConfigItem[] = [
    {
      title: "Home",
      route: routes.home,
      icon: <House />,
      root: true,
    },
    ...(isAdmin
      ? [
          {
            title: "Usuários",
            route: routes.home,
            icon: <FiUsers />,
            root: true,
          },
        ]
      : []),
  ];

  return (
    <Sidebar collapsible="icon">
      <SidebarHeader className="flex flex-row items-center justify-between group-data-[collapsible=icon]:justify-center group-data-[collapsible=icon]:mt-4 group-data-[collapsible=icon]:mb-4">
        <div className="group-data-[collapsible=icon]:hidden">
          <Image src="/logo-Kittl.svg" width={180} height={20} alt="" />
        </div>
        <SidebarTrigger className="group-data-[collapsible=icon]:mt-2 !p-0 max-md:hidden" />
      </SidebarHeader>
      <SidebarContent>
        <SidebarSeparator className="hidden group-data-[collapsible=icon]:block" />
        <SidebarGroup>
          <SidebarMenu>
            {mockItems.map((item) => (
              <SiderbarItem key={item.title} item={item} />
            ))}
          </SidebarMenu>
        </SidebarGroup>
      </SidebarContent>
      <SidebarRail />
    </Sidebar>
  );
}
