'use client'
import { Button } from "@/components/ui/button";
import { redirect, useRouter } from "next/navigation";

export default function Home() {

  return (
    redirect("/login")
  );
}
