import styles from "./admin-reports.module.scss"

export default function Reports() {
  return (
    <div className={styles.reportsContainer}>
      {/* Adverts Section */}
      <div className={styles.section}>
        <div className={styles.header}>
          <h2 className={styles.h2}>Adverts</h2>
          <div className={styles.filters}>
            <input type="date" className={styles.input} placeholder="Beginning date" />
            <input type="date" className={styles.input} placeholder="Ending date" />
            <select className={styles.select}>
              <option>Category</option>
            </select>
            <select className={styles.select}>
              <option>Type</option>
            </select>
            <select className={styles.select}>
              <option>Status</option>
            </select>
          </div>
        </div>
        <button className={styles.printButton}>
          <img src="/assets/vectors/print.svg" alt="Print" />
        </button>
      </div>

      {/* Most Popular Properties Section */}
      <div className={styles.section}>
        <div className={styles.header}>
          <h2 className={styles.h2}>Most Popular Properties</h2>
          <div className={styles.filters}>
            <input type="text" className={styles.input} placeholder="Amount" />
          </div>
        </div>
        <button className={styles.printButton}>
          <img src="/assets/vectors/print.svg" alt="Print" />
        </button>
      </div>

      {/* Users Section */}
      <div className={styles.section}>
        <div className={styles.header}>
          <h2 className={styles.h2}>Users</h2>
          <div className={styles.filters}>
            <select className={styles.select}>
              <option>Roles</option>
            </select>
          </div>
        </div>
        <button className={styles.printButton}>
          <img src="/assets/vectors/print.svg" alt="Print" />
        </button>
      </div>

      {/* Tour Requests Section */}
      <div className={styles.section}>
        <div className={styles.header}>
          <h2 className={styles.h2}>Tour Requests</h2>
          <div className={styles.filters}>
            <input type="date" className={styles.input} placeholder="Beginning date" />
            <input type="date" className={styles.input} placeholder="Ending date" />
            <select className={styles.select}>
              <option>Status</option>
            </select>
          </div>
        </div>
        <button className={styles.printButton}>
          <img src="/assets/vectors/print.svg" alt="Print" />
        </button>
      </div>
    </div>
  );
}
