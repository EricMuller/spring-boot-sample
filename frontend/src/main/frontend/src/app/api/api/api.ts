export * from './question.service';
import { QuestionService } from './question.service';
export * from './user.service';
import { UserService } from './user.service';

export const APIS = [QuestionService, UserService];
