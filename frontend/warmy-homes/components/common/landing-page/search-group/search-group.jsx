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
            <input className={styles.radio} type='radio' name='advert_type_id' id='Rent' value={1} />
            <label className={styles.radioLabel} htmlFor='Rent'>Rent</label>
            <div style={{ opacity: '40%', fontWeight: 'lighter' }}>|</div>
            <input className={styles.radio} type='radio' name='advert_type_id' id='Sale' value={2} />
            <label className={styles.radioLabel} htmlFor='Sale'>Sale</label>
          </div>

          <div className={styles.searchBox}>
            <input type='text' name='q' />
            <button className={styles.button} type='submit'><FaSearch />
            </button>
          </div>

          <div className={styles.advertCategories}>
            <input type='checkbox' name='category_id' id='house' value={1} />
            <label htmlFor='house' >House</label>
            <input type='checkbox' name='category_id' id='apartment' value={2} />
            <label htmlFor='apartment' >Apartment</label>
            <input type='checkbox' name='category_id' id='villa' value={4} />
            <label htmlFor='villa' >Villa</label>
            <input type='checkbox' name='category_id' id='office' value={3} />
            <label htmlFor='office' >Office</label>
          </div>
        </form>


      </div>


    </div>
  )
}
