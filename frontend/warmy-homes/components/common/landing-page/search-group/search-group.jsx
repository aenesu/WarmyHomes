import styles from "./search-group.module.scss";
import { FaSearch } from "react-icons/fa";

export default function SearchGroup() {
  return (

    <div className={styles.searchGroup}>

      <div className={styles.motto}>
        Easy Way to Find a <br /> Perfect Properity
      </div>

      <div className={styles.searchBoxGroup}>

        <form action='/properties' method='get'>
          
          <div className={styles.rentSaleSelector}>
            <input className={styles.radio} type='radio' name='rent-sale' id='Rent' value={'Sale'} />
            <label className={styles.radioLabel} htmlFor='Rent'>Rent</label>
            <div style={{ opacity: '40%', fontWeight: 'lighter' }}>|</div>
            <input className={styles.radio} type='radio' name='rent-sale' id='Sale' value={'Sale'} />
            <label className={styles.radioLabel} htmlFor='Sale'>Sale</label>
          </div>

          <div className={styles.searchBox}>
            <input type='text' name='search-term' />
            <button className={styles.button} type='submit'><FaSearch />
            </button>
          </div>

          <div className={styles.advertCategories}>
            <input type='checkbox' name='house' id='house' />
            <label htmlFor='house' >House</label>
            <input type='checkbox' name='apartment' id='apartment' />
            <label htmlFor='apartment' >Apartment</label>
            <input type='checkbox' name='villa' id='villa' />
            <label htmlFor='villa' >Villa</label>
            <input type='checkbox' name='office' id='office' />
            <label htmlFor='office' >Office</label>
          </div>
        </form>


      </div>


    </div>
  )
}
