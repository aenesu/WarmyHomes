"use client"; 
import { useEffect, useState } from "react";
import AdminLayout from "@/components/admin/admin-layout/admin-layout";
import { Akshar } from "next/font/google";
import { Advent_Pro } from "next/font/google";
import '@/styles/styles.scss';

const akshar = Akshar({ subsets: ["latin"], weight: ["300", "400", "500", "600", "700"] });
const adventPro = Advent_Pro({ subsets: ["latin"], weight: ["400", "700"] });

export default function AdminRootLayout({ children }) {
  const [isClient, setIsClient] = useState(false);

 
  useEffect(() => {
    setIsClient(true);
  }, []);

  
  if (!isClient) {
    return null;
  }

  return (
    <html lang="en">
      <body className={`${akshar.className}`}>
        <AdminLayout>
          {children}
        </AdminLayout>
      </body>
    </html>
  );
}
