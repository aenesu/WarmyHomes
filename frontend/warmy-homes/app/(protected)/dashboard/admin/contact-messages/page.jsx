"use client";
import React, { useState, useEffect } from 'react';
import AdminContactMessagesCard from "@/components/admin/admin-contact-card/admin-contact-card";
import Pagination from '@/components/common/pagination/pagination';
import styles from "./admin-contact-messages.module.scss";

const exampleMessages = [
  { name: "Aaron Hank", email: "aaron@example.com" },
  { name: "Hank Aaron", email: "hank@example.com" },
  { name: "Jane Doe", email: "jane@example.com" },
  { name: "John Smith", email: "john@example.com" },
  { name: "Emily Davis", email: "emily@example.com" },
  { name: "David Davis", email: "david@example.com" },
];

export default function AdminContactMessages() {
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 4;

  // Handle page changes
  const handlePageChange = (page) => {
    if (page >= 1 && page <= totalPages) {
      setCurrentPage(page);
    }
  };

  // Calculate pagination
  const totalPages = Math.ceil(exampleMessages.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedMessages = exampleMessages.slice(startIndex, startIndex + itemsPerPage);

  useEffect(() => {
    console.log('Current Page:', currentPage);
    console.log('Paginated Messages:', paginatedMessages);
  }, [currentPage, paginatedMessages]);

  return (
    <div className={styles.container}>
      <div className={styles.searchSection}>
        <input
          type="text"
          className={styles.searchInput}
          placeholder="Type something..."
        />
        <button className={styles.searchButton}>
          <img src="/assets/vectors/magnify.svg" alt="Search" />
        </button>
        <select className={styles.dropdown}>
          <option>All messages</option>
          <option>Read</option>
          <option>Unread</option>
        </select>
      </div>

      <div className={styles.unreadMessagesLabel}>
        Unread Messages
      </div>
      
      <div className={styles.header}>
        <h4>Name</h4>
        <h4>Email</h4>
        <h4>Actions</h4>
      </div>

      <div className={styles.messagesList}>
        {paginatedMessages.map((message, index) => (
          <AdminContactMessagesCard key={index} name={message.name} email={message.email} />
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
