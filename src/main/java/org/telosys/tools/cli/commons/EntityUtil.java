/**
 *  Copyright (C) 2015-2017  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.tools.cli.commons;

import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.telosys.tools.cli.Environment;
import org.telosys.tools.generic.model.Entity;

public class EntityUtil {

	/**
	 * No constructor ! 
	 */
	private EntityUtil() {
	}
		
	/**
	 * Filters the given entities according with the given criteria
	 * @param allEntities
	 * @param criteria
	 * @return
	 */
	public final static List<Entity> filter( List<Entity> allEntities, List<String> criteria ) {
		List<Entity> list = select(allEntities, criteria);
		sort(list);
		return list;
	}
	
	public final static List<String> toEntityNames( List<Entity> entities ) {
		List<String> list = new LinkedList<String>();
		for ( Entity e : entities ) {
			list.add(e.getClassName());
		}
		return list ;
	}
	
	/**
	 * Selects the entities matching the given criteria
	 * @param allEntities
	 * @param criteria
	 * @return
	 */
	public final static List<Entity> select( List<Entity> allEntities, List<String> criteria ) {
		if ( criteria != null ) {
			Map<String,Entity> map = new Hashtable<String,Entity>();
			for ( String criterion : criteria ) {
				for ( Entity entity : allEntities ) {
					String entityClassName = entity.getClassName() ;
					if ( entityClassName.contains(criterion) ) {
						map.put(entityClassName, entity); 
					}
				}				
			}
			// Convert map values to list
			List<Entity> list = new LinkedList<Entity>();
			for ( Entity entity : map.values() ) {
				list.add(entity);
			}
			return list;	
		}
		else {
			return allEntities ; // No critera => ALL
		}
	}

	/**
	 * Sorts the given list of TargetDefinition by template name
	 * @param list
	 */
	public final static void sort( List<Entity> list) {
		// Sort 
		Collections.sort(list, new Comparator<Entity>() {
			@Override
	        public int compare(Entity e1, Entity e2) {
	        	return e1.getClassName().compareTo(e2.getClassName());
	        } 
		});
	}
	
	//--------------------------------------------------------------------------------------------
	// FORMATED OUTPUT
	//--------------------------------------------------------------------------------------------
	public static List<String> buildList( List<Entity> entities ) {
		if ( entities != null && entities.size() > 0 ) {
			List<String> list = new LinkedList<String>();
			for ( Entity entity : entities ) {
				list.add( buildLine(entity) );
			}
			return list;
		}
		else {
			return new LinkedList<String>();
		}
	}

	public static String buildListAsString( List<Entity> entities ) {
		if ( entities != null && entities.size() > 0 ) {
			StringBuffer sb = new StringBuffer();
			for ( Entity entity : entities ) {
				sb.append( buildLine(entity) );
				sb.append(Environment.LINE_SEPARATOR);
			}
			return sb.toString();
		}
		else {
			return "No entity";
		}
	}

	private static String buildLine(Entity e) {
		return " . " + e.getClassName() + " ( " + e.getFullName() + " )" ;
	}

}
