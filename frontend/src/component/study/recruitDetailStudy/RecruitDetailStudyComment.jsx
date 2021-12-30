import React, { PureComponent } from "react";
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";
import Textarea from "react-autosize-textarea";
class RecruitDetailStudyComment extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style.comment_title}>댓글</div>
            <form>
              <div className={style.comment_container}>
                <div className={style.comment_writer}>부엉이</div>
                <Textarea autoComplete="off" placeholder="댓글을 입력하세요" style={{width:"90%", border: "none", resize:"none"}}/>
                <div className={style.comment_submit_wrap}>
                  <button className={style.comment_submit} type="submit">
                    등록
                  </button>
                </div>
              </div>
            </form>
            <div className={style.view_container}>
              <ul>
                <li>
                  <div className={style.view_writer}>부엉이</div>
                  <div className={style.view_comment}>
                    Lorem, ipsum do excepturi sint veniam?
                  </div>
                  <div className={style.view_info}>
                    <ul>
                     <li>2021-11-18</li>
                     <li>답글</li>
                     <li>수정</li>
                     <li>삭제</li>
                    </ul> 
                  </div>
                  <div className={style.comment_partition} />
                </li>
              </ul>
            </div>
          </React.Fragment>
        );
    }
}

export default RecruitDetailStudyComment;
