"use client";
import React, { useState } from 'react';
import UsersCard from "@/components/admin/admin-users-card/admin-users-card";
import Pagination from '@/components/common/pagination/pagination';
import styles from "./admin-users.module.scss";

const users = [
  { id: 1, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
  { id: 2, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
  { id: 3, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
  { id: 4, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
  { id: 5, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
  { id: 6, name: "Aaron Hank", email: "example@example.com", phone: "(454)454 45 45" },
];

export default function AdminUsers() {
  const [searchTerm, setSearchTerm] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 4;

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    setCurrentPage(1); 
  };

  const handlePageChange = (page) => {
    if (page >= 1 && page <= totalPages) {
      setCurrentPage(page);
    }
  };

  // Filter users based on search term
  const filteredUsers = users.filter(user =>
    user.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    user.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
    user.phone.includes(searchTerm)
  );

  // Calculate pagination
  const totalPages = Math.ceil(filteredUsers.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedUsers = filteredUsers.slice(startIndex, startIndex + itemsPerPage);

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
      </div>

      <div className={styles.header}>
        <div>Name</div>
        <div>Email</div>
        <div>Phone</div>
        <div>Actions</div>
      </div>

      <div className={styles.cards}>
        {paginatedUsers.map(({ id, name, email, phone }) => (
          <UsersCard
            key={id}
            name={name}
            email={email}
            phone={phone}
          />
        ))}
      </div>

      {/* Pagination Component */}
      <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={handlePageChange}
      />
    </div>
  );
}
