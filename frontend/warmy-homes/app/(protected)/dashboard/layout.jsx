"use client";
import { useEffect, useState } from "react";
import { Akshar } from "next/font/google";
import { Advent_Pro } from "next/font/google";
import "@/app/global.css";
import '@/styles/styles.scss';
import styles from "./admin-layout.module.scss"
import Sidebar from "@/components/admin/sidebar/sidebar";

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
      <body className={`${akshar.className} bodyAutoOverflow`}>
        <div className={styles.container}>
          <Sidebar />
          <div className={styles.mainContent}>
            {children}
          </div>
        </div>
      </body>
    </html>
  );
}
