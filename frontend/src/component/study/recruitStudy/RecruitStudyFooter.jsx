import React, { PureComponent } from "react";
import style from "../../../css/study/study_recruit/study_recruit.module.css";
import { Link } from "react-router-dom";
class RecruitStudyFooter extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style["main__tab-make-wrap"]}>
              <div className={style["main__tab-make"]}>
                <Link>개설</Link>
              </div>
            </div>
            <div className={style["main__tab-list"]}>
              <Link>1</Link>
            </div>
          </React.Fragment>
        );
    }
}

export default RecruitStudyFooter;
