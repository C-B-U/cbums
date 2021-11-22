import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
import style from "../../../css/study/study_workJournal/study_workJournal.module.css";
class StudyActivityFooter extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["main__writing"]}>
          <button className={style["main__writing-button"]} type="button">
            글쓰기
          </button>
        </div>
        <div className={style["main__page-box"]}>
          {/* 페이지 수 */}
          <Link to="/">
            <div className={style["main__page-box-number"]}>1</div>
          </Link>
        </div>
      </React.Fragment>
    );
  }
}

export default StudyActivityFooter;
