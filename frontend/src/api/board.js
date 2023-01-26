import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleNo, success, fail) {
  api.get(`/board/${articleNo}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleNo, success, fail) {
  api.delete(`/board/${articleNo}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
