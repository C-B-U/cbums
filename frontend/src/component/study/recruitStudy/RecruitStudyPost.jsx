import React, { PureComponent } from "react";
import style from "../../../css/study/study_recruit/study_recruit.module.css";
import { Link } from "react-router-dom";
class RecruitStudyPost extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <Link to="/">
              <div className={style.post_wrap}>
                <div className={style.post_title}>
                  <span
                    className={`${style.post_status} ${style.post_status_unrecruited}`}
                  >
                    모집중
                  </span>
                  <div className={style.title_text}>
                    {this.props.studyName}
                  </div>
                </div>
                <div className={style.post_tags}>
                  <ul className={style["main__slide-tag-list"]}>
                    <li className={style["main__slide-tag-list-item"]} key="1">
                      스터디
                    </li>
                    <li className={style["main__slide-tag-list-item"]} key="2">
                      JAVA
                    </li>
                  </ul>
                </div>
                <div className={style.post_info}>
                  <span className={style.info_name}>{this.props.userName}</span>
                  <span className={style.info_date}>{this.props.postDate}</span>
                </div>
                <div className={style.detail_wrap}>
                  <div className={style.comment_wrap}>
                    <div className={style.comment_number}>0</div>
                    <div style={{ fontSize: "0.8em", color: "grey" }}>댓글</div>
                  </div>
              
                  <div className={style.head_count}>모집인원: 4</div>
                </div>
                <div className={style.partition} />
              </div>
            </Link>
          </React.Fragment>
        );
    }
}

export default RecruitStudyPost;
