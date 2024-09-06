"use client"
import CategoriesCard from "@/components/admin/categories-card/categories-card";
import styles from "./categories.module.scss";
import { useState } from "react";
import Link from "next/link";

export default function AdminCategories() {
  const [searchTerm, setSearchTerm] = useState("");
  const Categories = [
    { id: 1, name:"Houses", icon: "/assets/images/categories/house.svg", sequence: 1, active: true },
    { id: 2, name: "Apartments", icon: "/assets/images/categories/apartment.svg", sequence: 2, active: true },
    { id: 3, name: "Offices", icon: "/assets/images/categories/office.svg",  sequence: 3, active: true },
    { id: 4, name: "Villas", icon: "/assets/images/categories/villas.svg", sequence: 4, active: true },
    { id: 5, name: "Bungalows", icon: "/assets/images/categories/bungalow.svg", sequence: 5, active: true },
  ];

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };


  return (
    <div className={styles.container}>
      <div className={styles.searchBar}>
        <input
          type="text"
          placeholder="Type something"
          value={searchTerm}
          onChange={handleSearchChange}
          className={styles.searchInput}
        />
        <button className={styles.searchButton}>
          <img src="/assets/vectors/magnify.svg" alt="Search" />
        </button>
        <Link href="/dashboard/admin/categories/new" className={styles.link}>
          <button className={styles.addButton}>ADD NEW</button>
        </Link>
      </div>
      <div className={styles.header}>
        <div>Icon</div>
        <div>Name</div>
        <div>Sequence</div>
        <div>Active</div>
        <div>Action</div>
      </div>
      <div className={styles.cards}>
        {Categories.map(({ id, icon, name, sequence, active }) => (
          <CategoriesCard
            key={id}
            icon={icon}
            name={name}
            sequence={sequence}
            active={active}
          />
        ))}
      </div>
    </div>
  );
}
